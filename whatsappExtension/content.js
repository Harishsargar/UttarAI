console.log("extension loaded");
// alert("WhatsApp Extension Loaded");


function findToolBar(){
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


// function getLastMessage() { 
//     // Get all message bubbles
//     const messageElements = document.querySelectorAll('div._amk4._amkd');
//     if (messageElements.length === 0) return null;
//     alert("Message Elements: " + messageElements.length);

//     // Get the last message bubble (latest one)
//     const lastContainer = messageElements[messageElements.length - 1];

//     let sender = 'them'
//     // Identify sender using aria-label
//     const senderSpan = lastContainer.querySelector('div._amk6._amlo > span[aria-label]');
//     // if (!senderSpan) return null;

//     if(senderSpan) {
//     const ariaLabel = senderSpan.getAttribute('aria-label') || '';
//         if(ariaLabel){
//             sender = ariaLabel.toLowerCase().includes('you') ? 'me' : 'them';
//         }
//     }

//     // Get message text
//     const messageSpan = lastContainer.querySelector('span._ao3e.selectable-text.copyable-text');
//     if (!messageSpan) return null;

//     const message = messageSpan.innerText.trim();

//     return {
//         sender: sender,
//         message: message
//     };
// }


function createAIButton(){
  const button = document.createElement('button');
  button.className = 'x1c4vz4f x2lah0s xdl72j9 xfect85 x1iy03kw x1lfpgzf';
  button.style.marginRight = '5px';
  button.style.marginBottom = '7px';
  button.style.padding='11px';
  button.style.fontSize = '14px';
  button.style.borderRadius = '8px';
  button.innerHTML = 'AI-Reply';
  button.style.backgroundColor = '#2A3942';
// alert("createAIButton called");
  return button;  
}

function injectButton(){
    console.log("inject button called");
    // alert("inject button called");

    // to remove the existing button if present
    const existingButton = document.querySelector('.ai-reply-button');
    if (existingButton) {
        existingButton.remove();
    }

    const toolbar = findToolBar();
  if(!toolbar){
    // alert("Toolbar not found");
    return;
  }
//   alert("Toolbar found");
  const button = createAIButton();
  button.classList.add('ai-reply-button');

    const conversationContext = getConversationContext();
    console.log(conversationContext)
    // alert("Conversation Context: " + conversationContext);

// ============================================================
//     const lastMessage = getLastMessage();

// if (lastMessage) {
//     alert("Last Message: " + lastMessage.message);
//     alert("Sender: " + lastMessage.sender);
// } else {
//     alert("No message found!");
// }

// ============================================================
  button.addEventListener('click', async()=>{

  

    try {
            button.innerHTML = 'Generating...';
            button.disabled = true;

            const conversationContext = getConversationContext();
            // const response = await fetch('http://localhost:8080/api/whatsapp/generate', {
            const response = await fetch('http://ai-replyer.ap-south-1.elasticbeanstalk.com/api/whatsapp/generate', {

                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
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
            button.disabled =  false;
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