document.getElementById("login").onclick = () => {

    chrome.storage.local.set({ signed_in: true }, () => {
        // Dynamically update popup
        chrome.action.setPopup({ popup: 'popup.html' }, () => {
            // Close popup so user clicks again and sees updated view
            window.close();
        });
    });

}