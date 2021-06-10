//console.log('script.js is working ')

function toggleMenu() {
//alert("hello ")
  const sidebar = document.getElementById("sidebar");
  const side_toggle = document.getElementById("side_toggle");

  if (sidebar.style.display === "block") {
    sidebar.style.display = "none";
    //side_toggle.style.display = "block";
  } else {
    sidebar.style.display = "block";
    //side_toggle.style.display = "none";
  }
}


