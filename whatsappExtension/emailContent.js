console.log("AI content script loaded here");
// alert("Extension test");



function findToolBar() {
  const selectors = [
    '.btC',
    '.aDh',
    '[role="toolbar"]',
    '.gU.Up'
  ];
  for (const selector of selectors) {
    const toolbar = document.querySelector(selector);
    if (toolbar) {
      return toolbar;
    }

  }
  return null;
}

function getEmailContent() {
  const selectors = [
    '.h7',
    '.a3s.aiL',
    '.gmail_quote',
    '[role="presentation"]'
  ];
  for (const selector of selectors) {
    const content = document.querySelector(selector);
    if (content) {
      return content.innerText.trim();
    }

  }
  return '';
}


function createAIButton() {
  const button = document.createElement('div');
  button.className = 'T-I J-J5-Ji aoO v7 T-I-atl L3';
  button.style.marginRight = '8px';
  button.style.borderRadius = '23px';
  button.style.backgroundColor = '#0b57d0';
  button.innerHTML = 'AI Reply';
  button.setAttribute('role', 'button');
  button.setAttribute('data-tooltip', 'Generate AI Reply');
  return button;
}

function getTokenFromStorage() {
  return new Promise(resolve => {
    chrome.storage.local.get('token', result => {
      resolve(result.token);
    });
  });
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

function removeTokenFromStorage() {
  return new Promise(res => {
    chrome.storage.local.remove('token', res);
  });
}

function injectButton() {
  console.log("injectButton called");
  // alert("injectButton called");
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
  // alert("Toolbar found");
  const button = createAIButton();
  button.classList.add('ai-reply-button');

  button.addEventListener('click', async () => {



    try {
      button.innerHTML = 'Generating...';
      button.disabled = true;
      let token;
      token = await getTokenFromStorage();
      //first we will check whether token is present in local storeage or not
      if (!token) {
        alert("You are not logged in Extension please login");
        return;
      }

      if(isTokenExpired(token)){
        await removeTokenFromStorage();
        alert("token is expire")
        return;
      }
      const emailContent = getEmailContent();
      // const response = await fetch('http://localhost:8080/api/secure/email/generate', {
        const response = await fetch('https://uttarai-kdfm.onrender.com/api/secure/email/generate', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({
          emailContent: emailContent,
          tone: "professional"
        })
      });
      if (response.status === 402) {
        // Handle 402 Payment Required
        alert("API call limit reached. Please upgrade your plan. Visit the website to upgrade your plan");
        return;
      }
      if (!response.ok) {
        throw new Error('API Request Failed');
      }

      const generatedReply = await response.text();
      const composeBox = document.querySelector('[role="textbox"][g_editable="true"]');

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
      (node.matches('.aDh') || node.querySelector('.aDh'))
    );
    if (hasComposeElement) {
      setTimeout(() => {
        injectButton();
        pingBackendEvery10Minutes();  // Will only start once
      }, 500);
    }
  }
});

observer.observe(document.body, {
  childList: true,
  subtree: true
})


// to ping the backend to spin the instance 


let backendPingStarted = false;
pingBackendEvery10Minutes(); 

function pingBackendEvery10Minutes() {
  if (backendPingStarted) return;
  backendPingStarted = true;

  // Call once immediately
  fetch("http://localhost:8080/api/ping/isbackendup")
    .then(() => console.log("Initial backend ping sent"))
    .catch((err) => console.error("Initial ping failed", err));

  // Then every 10 minutes
  setInterval(() => {
    fetch("http://localhost:8080/api/ping/isbackendup")
      .then(() => console.log("Backend ping sent"))
      .catch((err) => console.error("Ping failed", err));
  }, 10 * 60 * 1000);
}
