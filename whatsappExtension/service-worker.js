chrome.runtime.onInstalled.addListener(() => {
  // Set default sign-in state
  // chrome.storage.local.set({ signed_in: false });
  // setPopupBasedOnSignIn();
  chrome.storage.local.get('token', (data) => {
    const popup = data.token ? 'popup.html' : 'popup_sign_in.html';
    chrome.action.setPopup({ popup });
  });
});

chrome.runtime.onStartup.addListener(() => {
  // Reset popup based on saved state on browser restart
  // setPopupBasedOnSignIn();
});

chrome.action.onClicked.addListener((tab) => {
  // Popup is already set; no need to change here anymore
  // chrome.storage.local.get('token', (data) => {
  //   const popup = data.token ? 'popup.html' : 'popup_sign_in.html';
  //   chrome.action.setPopup({ popup });
  // });
});

// Helper to set popup dynamically
// function setPopupBasedOnSignIn() {
//   chrome.storage.local.get('signed_in', (data) => {
//     const popup = data.signed_in ? 'popup.html' : 'popup_sign_in.html';
//     chrome.action.setPopup({ popup });
//   });
// }


// this will get the jwt from our website and give this token to the content.js to make the api call..
chrome.runtime.onMessage.addListener((message, sender, sendResponse) => {
  if (message.type === "getJWT") {
    chrome.cookies.get({ url: "http://localhost:5173", name: "jwt" }, (cookie) => {
      if (cookie) {
        sendResponse({ token: cookie.value });
      } else {
        sendResponse({ token: null });
      }
    });
    return true; // keep the message channel open for async response
  }
});

