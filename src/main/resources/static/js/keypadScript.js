var keypad;

(function($) {})(jQuery);

keypad = (function() {
    var color, colorOver, delSize, keySize, keypadSize, text, _cont, _disable, _disp;

    _disable = false;

    _cont = 0;

    _disp = 0;

    keypadSize = {
        'width': '300',
        'height': '500',
        'display': 'block'
    };

    color = {
        'background': 'white',
        'border-bottom': 'solid #E0E0E0 3px',
        'color': '#333'
    };

    colorOver = {
        "background": '#BCE8F1',
        'border-bottom': 'solid #3A87AD 3px',
        'color': '#3A87AD'
    };

    text = {
        'font-family': 'arial',
        'font-size': '35pt',
        'line-height': '4',
        'text-align': 'center'
    };

    keySize = {
        'margin': '1px',
        'width': '32%',
        'height': '24%',
        'float': 'left'
    };

    delSize = {
        'margin': '1px',
        'width': '65%',
        'height': '24%',
        'float': 'left'
    };

    function keypad(container, display) {
        _cont = container;
        _disp = display;
        this.create(container, display);
    }


    /*
    		Methos
     */

    keypad.prototype.create = function(cont, dis) {
        $("#" + cont).append(function() {
            return "<div class='" + _cont + "key'>1</div> <div class='" + _cont + "key'>2</div> <div class='" + _cont + "key'>3</div> <div class='" + _cont + "key'>4</div> <div class='" + _cont + "key'>5</div> <div class='" + _cont + "key'>6</div> <div class='" + _cont + "key'>7</div> <div class='" + _cont + "key'>8</div> <div class='" + _cont + "key'>9</div> <div class='" + _cont + "key'>0</div> <div class='" + _cont + "del'>&#8636;</div>";
        });
        $("#" + _cont).css(keypadSize);
        $("." + _cont + "key").click(function(e) {
            if (_disable) {
                return false;
            }
            return $("#" + _disp).val($('#' + _disp).val() + e.currentTarget.textContent);
        }).css(color).css(keySize).css(text).mouseover(function() {
            return $(this).css(colorOver);
        }).mouseout(function() {
            return $(this).css(color);
        });
        return $("." + _cont + "del").click(function(e) {
            if (_disable) {
                return false;
            }
            return $("#" + _disp).val('');
        }).css(color).css(delSize).css(text).mouseover(function() {
            return $(this).css(colorOver);
        }).mouseout(function() {
            return $(this).css(color);
        });
    };

    keypad.prototype.changeKeyColor = function(rgb) {
        color.background = rgb;
        $("." + _cont + "key").css({
            "background": rgb
        });
        return $("." + _cont + "del").css({
            "background": rgb
        });
    };

    keypad.prototype.changeKeyColorHover = function(rgb) {
        colorOver.background = rgb;
        $("." + _cont + "key").mouseover(function() {
            return $(this).css({
                "background": rgb
            });
        });
        return $("." + _cont + "del").mouseover(function() {
            return $(this).css({
                "background": rgb
            });
        });
    };

    keypad.prototype.changeBorderColor = function(border) {
        color['border-bottom'] = border;
        $("." + _cont + "key").css({
            "border-bottom": border
        });
        return $("." + _cont + "del").css({
            "border-bottom": border
        });
    };

    keypad.prototype.changeBorderColorHover = function(border) {
        colorOver['border-bottom'] = border;
        $("." + _cont + "key").mouseover(function() {
            return $(this).css({
                "border-bottom": border
            });
        });
        return $("." + _cont + "del").mouseover(function() {
            return $(this).css({
                "border-bottom": border
            });
        });
    };

    return keypad;

})();