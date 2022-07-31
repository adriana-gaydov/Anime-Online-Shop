$('#hide').click(function() {
    if ($("#hide:checked").length === 1) {
        $("#address-field").removeClass('address-field-hide');
        $("#address-field-user").addClass('address-field-hide');
    }
    else {
        $("#address-field").addClass('address-field-hide');
        $("#address-field-user").removeClass('address-field-hide');
    }
});