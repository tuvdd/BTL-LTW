function showEditModal(tr_id) {
    document.getElementById('edit-modal').style.display = "block";
    edit_hidden = document.getElementById("edit-hidden");
    edit_name = document.getElementById("edit-name");
    edit_status = document.getElementById("edit-status");
    tr = document.getElementById(tr_id)

    edit_hidden.value = tr.children[0].innerHTML
    edit_name.value = tr.children[1].innerHTML
    edit_status.checked = tr.children[2].innerHTML == 'true'
}

function closeEditModal() {
    document.getElementById('edit-modal').style.display = "none";
}