
var paragraph = document.getElementById("myParagraph");
if (paragraph.scrollHeight > paragraph.clientHeight) {
      showMoreButton.style.display = "inline"; /* Hiển thị nút "Xem thêm" */
}

function showMore() {
    var paragraph = document.getElementById("myParagraph");
    var showMoreButton = document.getElementById("showMoreButton");
    var showLessButton = document.getElementById("showLessButton");
    paragraph.style.maxHeight = "none"; /* Hiển thị toàn bộ nội dung */
    showMoreButton.style.display = "none"; /* Ẩn nút "Xem thêm" */
    showLessButton.style.display = "inline"; /* Hiển thị nút "Ẩn bớt" */
}

  function showLess() {
    var paragraph = document.getElementById("myParagraph");
    var showMoreButton = document.getElementById("showMoreButton");
    var showLessButton = document.getElementById("showLessButton");
    paragraph.style.maxHeight = "90px"; /* Giới hạn chiều cao nội dung */
    showMoreButton.style.display = "inline"; /* Hiển thị nút "Xem thêm" */
    showLessButton.style.display = "none"; /* Ẩn nút "Ẩn bớt" */
  }
 