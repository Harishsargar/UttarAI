chrome.runtime.onInstalled.addListener(() => {
  // Set default sign-in state
  chrome.storage.local.set({ signed_in: false });
  setPopupBasedOnSignIn();
});

chrome.runtime.onStartup.addListener(() => {
  // Reset popup based on saved state on browser restart
  setPopupBasedOnSignIn();
});

chrome.action.onClicked.addListener((tab) => {
  // Popup is already set; no need to change here anymore
});

// Helper to set popup dynamically
function setPopupBasedOnSignIn() {
  chrome.storage.local.get('signed_in', (data) => {
    const popup = data.signed_in ? 'popup.html' : 'popup_sign_in.html';
    chrome.action.setPopup({ popup });
  });
}
