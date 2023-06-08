function showEditModal(tr_id) {
    document.getElementById('edit-modal').style.display = "block";
    edit_hidden = document.getElementById("edit-hidden");
    edit_name = document.getElementById("edit-name");
    edit_author = document.getElementById("edit-author");
    edit_release_year = document.getElementById("edit-release_year");
    edit_price = document.getElementById("edit-price");
    edit_promote_price = document.getElementById("edit-promote_price");
    edit_description = document.getElementById("edit-description");
    edit_sub_description = document.getElementById("edit-sub_description");
    tr = document.getElementById(tr_id)

    edit_hidden.value = tr.children[0].innerHTML
    edit_name.value = tr.children[1].innerHTML
    edit_author.value = tr.children[3].innerHTML
    edit_release_year.value = tr.children[4].innerHTML
    edit_price.value = tr.children[6].innerHTML
    edit_promote_price.value = tr.children[7].innerHTML
    edit_description.value = tr.children[8].innerHTML
    edit_sub_description.value = tr.children[9].innerHTML
}

function closeEditModal() {
    document.getElementById('edit-modal').style.display = "none";
}