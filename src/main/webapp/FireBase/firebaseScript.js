// Your web app's Firebase configuration
var firebaseConfig = {
    apiKey: "AIzaSyC9ybR35m8IViCh9VNqjCNEDQrQjJXIAiY",
    authDomain: "progettotomcat.firebaseapp.com",
    databaseURL: "https://progettotomcat.firebaseio.com",
    projectId: "progettotomcat",
    storageBucket: "progettotomcat.appspot.com",
    messagingSenderId: "886153527719",
    appId: "1:886153527719:web:dd02f2fdc12c02b7deb682"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

// graphic interface
var ui = new firebaseui.auth.AuthUI(firebase.auth());

ui.start("#firebaseui-auth-container", {
  signInOptions: [
    firebase.auth.EmailAuthProvider.PROVIDER_ID,
    firebase.auth.GoogleAuthProvider.PROVIDER_ID
  ],
  signInSuccessUrl: "index.jsp",
  signInFlow: 'popup',
  callbacks: {
    uiShown: function () {
      $("#loader").hide();
    }
  }
});


