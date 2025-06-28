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
  button.style.marginRight = '5px';
  button.style.marginBottom = '7px';
  button.style.padding = '11px';
  button.style.fontSize = '14px';
  button.style.borderRadius = '8px';
  button.innerHTML = 'AI-Reply';
  button.style.backgroundColor = '#2A3942';
  // alert("createAIButton called");
  return button;
}

// checks if the token is expire or not before calling the api.
function isTokenExpired(token) {
  try {
    const payload = JSON.parse(atob(token.split('.')[1]));
    return Date.now() >= payload.exp * 1000;
  } catch (e) {
    return true; // if decoding fails, assume it's bad
  }
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
      chrome.storage.local.get('token', result => {
        token = result.token;
      })

      //first we will check whether token is present in local storeage or not
      if (!token) {
        // 🔁 Get JWT from background
        const jwtResponse = await new Promise((resolve) => {
          chrome.runtime.sendMessage({ type: "getJWT" }, resolve);
        });

        // if token not found in our website this alert will be shown
        if (!jwtResponse.token) {
          // should redirect to our website for login....
          alert('JWT not found. Please login first.');
          return;
        }

        // if found that means users is login and we will store it in the localstorage || in the chrome local storage
        // so we dont have to check in our websites cookies again and again
        // localStorage.setItem('token',jwtResponse.token);
        chrome.storage.local.set({ token: jwtResponse.token })
        // token = localStorage.getItem('token');
        chrome.storage.local.get('token', result => {
          token = result.token;
        })

      }

      //check if token is expire
      if (isTokenExpired(token)) {
        // redirect to our website
        chrome.storage.local.remove('token', () => {
          alert('token is expire please login again from our website!!');
        });
        // localStorage.removeItem('token');   // should remove the old token
        // alert('token is expire please login again from our website!!')
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
      alert('Failed to generate reply');
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