/* focusToEnd -- jQuery plugin from http://stackoverflow.com/a/11756980
 * */
(function($) {
    $.fn.focusToEnd = function() {
        return this.each(function() {
            var v = $(this).val();
            $(this).focus().val("").val(v);
        });
    };
})(jQuery);

$(document).ready(function() {
    var $editButton = $("#edit-page");

    if ($editButton.length) {
        // we are on the view page, so allow <Enter> to start editing
        $('body').keydown(function(e) {
            if (e.which == 13) {
                // jQuery's .click() is only JS events, so we have to use
                // the raw DOM to activate the link
                $editButton.get(0).click();
            }
        });
    } else {
        // editing page, so focus the textarea
        $('textarea').focusToEnd();
    }
});