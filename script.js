document.addEventListener("DOMContentLoaded", function () {
  const darkModeToggle = document.getElementById("darkModeToggle");
  const isDarkMode = localStorage.getItem("darkMode") === "true";
  const changeIcon = () => {
    darkModeToggle.innerHTML = darkModeToggle.innerHTML === "🌙" ? "☀️" : "🌙";
  };

  if (isDarkMode) {
    document.body.classList.add("dark-mode");
    darkModeToggle.innerHTML = "☀️";
  } else {
    darkModeToggle.innerHTML = "🌙";
  }

  darkModeToggle.addEventListener("click", () => {
    changeIcon();
    document.body.classList.toggle("dark-mode");
    localStorage.setItem(
      "darkMode",
      document.body.classList.contains("dark-mode")
    );
  });
});

let i = 0;
let txt = "The official documentation for RestDB4J, be welcome!"; 
let speed = 50;

function typeWriter() {
  if (i < txt.length) {
    document.getElementById("typing-effect").innerHTML += txt.charAt(i);
    i++;
    setTimeout(typeWriter, speed);
  }
}

typeWriter();
