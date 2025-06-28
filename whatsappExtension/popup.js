document.getElementById("logout").onclick = () => {
    // chrome.storage.local.set({ signed_in: false }, () => {
    //     // Dynamically update popup
    // chrome.action.setPopup({ popup: 'popup_sign_in.html' }, () => {
    //     // Close popup so user clicks again and sees updated view
    //     window.close();
    // });
    // });


    chrome.cookies.remove(
        {
            url: "http://localhost:5173",
            name: "jwt"
        },
        (details) => {
            if (details) {
                alert("Cookie removed");
            } else {
                alert("No matching cookie found to remove.");
            }

            // After removing the cookie, remove the token
            chrome.storage.local.remove('token', () => {
                alert('Token expired. Please login again from our website!');

                // After removing token, change popup
                chrome.action.setPopup({ popup: 'popup_sign_in.html' }, () => {
                    // Close current popup
                    window.close();
                });
            });
        }
    );

}