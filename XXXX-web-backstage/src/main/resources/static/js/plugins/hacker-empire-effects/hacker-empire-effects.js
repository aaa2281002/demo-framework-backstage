$(function(){
    function init() {
        var canvas = document.getElementById("hacker-empire-effects");
        var ctx = canvas.getContext("2d");
        //获取当前页面宽
        canvas.width = window.innerWidth;
        //获取当前页面高
        canvas.height = window.innerHeight;
        //显示字符
        var str = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM,./|~;'[]{}\"`.:?<>-+*!@#$%^&*()";
        //每个字符所占宽
        var lie = window.innerWidth / 20;
        //Y轴
        var posY = [];
        for (var x = 0; x < lie; x++) {
            posY[x] = 0;
        }
        // console.log(posY)
        // console.log(lie)
        function loop() {
            ctx.fillStyle = "rgba(0,0,0,0.05)";
            ctx.fillRect(0, 0, canvas.width, canvas.height);
            for (var i = 0; i < lie; i++) {
                ctx.font = "20px 微软雅黑";
                ctx.fillStyle = "green";
                //显示的值， X轴坐标， Y轴坐标
                ctx.fillText(str.charAt(Math.floor(Math.random()*str.length)),i*20,posY[i]*20);
                // ctx.fillText(String.fromCharCode(65 + Math.random() * 62), i * 20, posY[i] * 20);
                posY[i]++;
                if (posY[i] * 20 > window.innerHeight) {
                    //0.9的随机数造成列消失的时间不一致，产生的列也随之改变
                    if (Math.random() > 0.9) {
                        posY[i] = 0;
                    }
                }
            }
        }
        setInterval(loop, 100);
    };

    init();
})