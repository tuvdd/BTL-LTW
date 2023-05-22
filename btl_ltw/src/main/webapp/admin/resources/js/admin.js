function showEditModal(tr_id) {
    document.getElementById('edit-modal').style.display = "block";
    edit_hidden = document.getElementById("edit-hidden");
    edit_name = document.getElementById("edit-name");
    edit_email = document.getElementById("edit-email");
    edit_phonenum = document.getElementById("edit-phonenum");
    edit_cccd = document.getElementById("edit-cccd");
    tr = document.getElementById(tr_id)

    edit_hidden.value = tr.children[0].innerHTML
    edit_name.value = tr.children[1].innerHTML
    edit_email.value = tr.children[2].innerHTML
    edit_phonenum.value = tr.children[3].innerHTML
    edit_cccd.value = tr.children[4].innerHTML
    }

function closeEditModal() {
    document.getElementById('edit-modal').style.display = "none";
}