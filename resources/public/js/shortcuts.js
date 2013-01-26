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

function activateViewingShorcuts() {
    // we are on the view page, so allow <Enter> to start editing
    var $editButton = $("#edit-page");

    function loadEditor() {
        var editUrl = $editButton.attr('href');
        $('body').load(editUrl);
        activateEditingShortcuts();
    }

    // load the editor when the shortcut is pressed
    $('body').keypress(function(e) {
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

function activateEditingShortcuts() {
    // editing page, so focus the textarea
    $('textarea').focusToEnd();

    // go back to viewing if escape is pressed
    $('body').keypress(function(e) {
        if (e.keyCode == 27 && !isViewingPage()) { // escape key
            e.preventDefault();

            // don't go back to viewing if we'd lose content
            var originalText = $('textarea').text();
            if (originalText == $('textarea').val()) {
                var viewUrl = $("#cancel-edit").attr("href");
                $('body').load(viewUrl);
            }
        }
    });
}

$(document).ready(function() {
    if (isViewingPage()) {
        activateViewingShorcuts();
    } else {
        activateEditingShortcuts();
    }
});