document.addEventListener('DOMContentLoaded', function () {
  const toggle = document.getElementById('nav-toggle')
  const navbar = document.getElementById('navbar')
  toggle.addEventListener('click', function () {
    navbar.classList.toggle('expander')
  })
  // Ocultar el menú cuando haces clic fuera de él
  window.addEventListener('click', function (event) {
    if (!navbar.contains(event.target) && !toggle.contains(event.target)) {
      navbar.classList.remove('expander')
    }
  })
})