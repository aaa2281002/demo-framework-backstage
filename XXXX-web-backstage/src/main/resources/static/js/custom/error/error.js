function randomNum() {
    return Math.floor(Math.random() * 9) + 1;
}

var loop1, loop2, loop3,
    time = 30, i = 0, number;
loop3 = setInterval(function () {
    var thirdDigit = $('.thirdDigit');
    if (i > 40) {
        clearInterval(loop3);
        if (thirdDigit && thirdDigit.length > 0) {
            thirdDigit.text($(".thirdDigit").attr("value"));
        }
    } else {
        if (thirdDigit && thirdDigit.length > 0) {
            thirdDigit.text(randomNum());
        }
        i++;
    }
}, time);
loop2 = setInterval(function () {
    var secondDigit = $('.secondDigit');
    if (i > 80) {
        clearInterval(loop2);
        if (secondDigit && secondDigit.length > 0) {
            secondDigit.text($(".secondDigit").attr("value"));
        }
    } else {
        if (secondDigit && secondDigit.length > 0) {
            secondDigit.text(randomNum());
        }
        i++;
    }
}, time);
loop1 = setInterval(function () {
    var firstDigit = $('.firstDigit');
    if (i > 100) {
        clearInterval(loop1);
        if (firstDigit && firstDigit.length > 0) {
            firstDigit.text($(".firstDigit").attr("value"));
        }
    } else {
        if (firstDigit && firstDigit.length > 0) {
            firstDigit.text(randomNum());
        }
        i++;
    }
}, time);