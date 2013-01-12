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

function isViewingPage() {
    // are we viewing or editing a page?
    var $editButton = $("#edit-page");
    return $editButton.length > 0;
}

function activateEditShortcut() {
    // we are on the view page, so allow <Enter> to start editing
    var $editButton = $("#edit-page");

    function loadEditor() {
        var editUrl = $editButton.attr('href');
        $('body').load(editUrl);
    }

    // load the editor when the shortcut is pressed
    $('body').keydown(function(e) {
        if (e.which == 13 && isViewingPage()) { // enter key
            loadEditor();
        }
    });

    // load the editor when 'edit' is clicked
    $editButton.click(function(e) {
        e.preventDefault();
        loadEditor();
    });
}

$(document).ready(function() {
    if (isViewingPage()) {
        activateEditShortcut();
    } else {
        // editing page, so focus the textarea
        $('textarea').focusToEnd();
    }
});