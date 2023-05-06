var modal = document.getElementById('add-new-modal');

var btn = document.getElementById("add-new-button");

// var span = document.getElementsByClassName("close")[0];

btn.onclick = function showModal() {
    modal.style.display = "block";
}

// span.onclick = function() {
//     modal.style.display = "none";
// }

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}