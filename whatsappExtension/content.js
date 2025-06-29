console.log("extension loaded");
// alert("WhatsApp Extension Loaded");


function findToolBar() {
  // alert("findToolBar called");
  const toolbar = document.querySelector('._ak1r');
  if (toolbar) {
    return toolbar;
  }
  return null;
}



function getConversationContext(count = 10) {
  const messageElements = document.querySelectorAll('div._amk4._amkd');
  const total = messageElements.length;
  if (total === 0) return [];

  const messages = [];

  // Loop through the last `count` messages (or fewer if not enough)
  const start = Math.max(0, total - count);
  for (let i = start; i < total; i++) {
    const container = messageElements[i];
    let sender = 'them';

    const senderSpan = container.querySelector('div._amk6._amlo > span[aria-label]');
    if (senderSpan) {
      const ariaLabel = senderSpan.getAttribute('aria-label') || '';
      if (ariaLabel.toLowerCase().includes('you')) {
        sender = 'me';
      }
    }

    const messageSpan = container.querySelector('span._ao3e.selectable-text.copyable-text');
    if (messageSpan) {
      const messageText = messageSpan.innerText.trim();
      if (messageText) {
        messages.push({
          sender: sender,
          message: messageText
        });
      }
    }
  }

  return messages;
}


function createAIButton() {
  const button = document.createElement('button');
  button.className = 'x1c4vz4f x2lah0s xdl72j9 xfect85 x1iy03kw x1lfpgzf';
  button.style.marginLeft = '7px'
  button.style.marginRight = '0px';
  button.style.marginBottom = '13px';
  button.style.padding = '15px';
  button.style.fontSize = '14px';
  button.style.borderRadius = '10px';
  button.innerHTML = 'AI-Reply';
  button.style.backgroundColor = '#242626';
  // alert("createAIButton called");
  return button;
}

function isTokenExpired(token) {
  if (!token) return true;

  try {
    const payload = JSON.parse(atob(token.split('.')[1]));
    const currentTime = Math.floor(Date.now() / 1000); // current time in seconds
    return payload.exp < currentTime;
  } catch (e) {
    console.error('Invalid token format:', e);
    return true; // Treat invalid token as expired
  }
}

function setTokenToStorage(token) {
  return new Promise(resolve => {
    chrome.storage.local.set({ token }, resolve);
  });
}

function getTokenFromStorage() {
  return new Promise(resolve => {
    chrome.storage.local.get('token', result => {
      resolve(result.token);
    });
  });
}

function removeTokenFromStorage() {
  return new Promise(res => {
    chrome.storage.local.remove('token', res);
  });
}


function injectButton() {
  console.log("inject button called");
  // alert("inject button called");

  // to remove the existing button if present
  const existingButton = document.querySelector('.ai-reply-button');
  if (existingButton) {
    existingButton.remove();
  }

  const toolbar = findToolBar();
  if (!toolbar) {
    // alert("Toolbar not found");
    return;
  }
  //   alert("Toolbar found");
  const button = createAIButton();
  button.classList.add('ai-reply-button');

  const conversationContext = getConversationContext();
  console.log(conversationContext)

  button.addEventListener('click', async () => {
    try {
      button.innerHTML = 'Generating...';
      button.disabled = true;

      let token;
      token = await getTokenFromStorage();

      //first we will check whether token is present in local storeage or not
      if (!token) {
        alert("You are not logged in to the Extension. Please log in");
        return;
      }


      if (isTokenExpired(token)) {
        await removeTokenFromStorage();
        alert("token is expire, Login in again")
        return;
      }

      const conversationContext = getConversationContext();

      const response = await fetch('http://localhost:8080/api/secure/whatsapp/generate', {
        // const response = await fetch('https://uttarai.onrender.com/api/whatsapp/generate', {

        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({
          conversation: conversationContext,
          tone: "friendly"
        })
      });

      if (!response.ok) {
        throw new Error('API Request Failed');
      }
      // alert("Response:susses " );

      const generatedReply = await response.text();
      const composeBox = toolbar.querySelector('[role="textbox"]');

      if (composeBox) {
        composeBox.focus();
        document.execCommand('insertText', false, generatedReply);
      } else {
        console.error('Compose box was not found');
      }
    } catch (error) {
      console.error(error);
      alert('Failed to generate reply. Please try reloading WhatsApp or logging in again.');
    } finally {
      button.innerHTML = 'AI Reply';
      button.disabled = false;
    }
  });


  toolbar.insertBefore(button, toolbar.firstChild);

}


const observer = new MutationObserver((mutations) => {
  for (const mutation of mutations) {
    const AddedNodes = Array.from(mutation.addedNodes)
    const hasComposeElement = AddedNodes.some(node =>
      node.nodeType === Node.ELEMENT_NODE &&
      (node.matches('._ak1r') || node.querySelector('._ak1r'))
    );
    if (hasComposeElement) {
      setTimeout(injectButton, 500)
    }
  }
});

observer.observe(document.body, {
  childList: true,
  subtree: true
})