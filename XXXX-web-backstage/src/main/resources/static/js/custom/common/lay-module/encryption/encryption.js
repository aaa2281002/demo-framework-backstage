/**
 * date:2019/12/11
 * author: 邋遢龘鵺
 * description:encrypt rsa加密引用
 */

layui.define(["layer"], function (exports) {
    this.encryptData = {
        keySize: 1024,
        privateKey: "",
        publicKey: "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlEBK1eTXU70PtaGcpxoIRlK7YqRLIb8DlPCVuYols+7pyoCiFPobPW5UOUcNsPoo15c1851cUW0nG++YNWZqEQmQ5TXzqB6KqNlv0oOhVImElTbpymL+qtX24uSAddEGEOWlgy8g2ImW1l3SzvDXST0CwPzDuyLRWjtJSxXoXRszhXRX1DvQNG0Nex72kX5/amODEmi8YI/gZ2qO3FhywUuYd0bHcdm7ni4uIhouN2hNkubwEj3wVhLpS733aPy1keePCaI+Us/Bh4d+cdUSwrEb+tLlhoxJekIdHWy5gnKT0iuEq9UI724YfyBHNP5PTbUoIx//ny2apkhjAtEDKQIDAQAB"
    };
    this.encryption = new function () {
        /**
         * 设置位数
         * @param publicKey
         */
        this.setKeySize = function (keySize) {
            encryptData.keySize = keySize;
        };

        /**
         * 设置私钥
         * @param publicKey
         */
        this.setPrivateKey = function (privateKey) {
            encryptData.privateKey = privateKey;
        };

        /**
         * 设置公钥
         * @param publicKey
         */
        this.setPublicKey = function (publicKey) {
            encryptData.publicKey = publicKey;
        };

        /**
         * 根据公钥加密
         * @param str
         * @returns string
         */
        this.encryptPublicKey = function (str) {
            var encryptJs = new JSEncrypt({
                default_key_size: encryptData.keySize
            });
            encryptJs.setPublicKey(encryptData.publicKey);
            var encrypted = encryptJs.encrypt(str);
            return encrypted
        };

        // /**
        //  * 根据私钥加密 - 暂时有问题，用私钥加密后公钥无法解决。
        //  * @param str
        //  * @returns string
        //  */
        // this.encryptPrivateKey = function (str) {
        //     var encryptJs = new JSEncrypt({
        //         default_key_size: encryptData.keySize
        //     });
        //     encryptJs.setPrivateKey(encryptData.privateKey);
        //     var encrypted = encryptJs.encrypt(str);
        //     return encrypted
        // };
        //
        // /**
        //  * 根据公钥解密
        //  * @param str
        //  * @returns string
        //  */
        // this.decryptPublicKey = function (str) {
        //     var decryptJs = new JSEncrypt({
        //         default_key_size: encryptData.keySize
        //     });
        //     decryptJs.setPublicKey(encryptData.publicKey);
        //     var decrypted = decryptJs.decrypt(str);
        //     return decrypted;
        // };

        /**
         * 根据私钥解密
         * @param str
         * @returns string
         */
        this.decryptPrivateKey = function (str) {
            var decryptJs = new JSEncrypt({
                default_key_size: encryptData.keySize
            });
            decryptJs.setPrivateKey(encryptData.privateKey);
            var decrypted = decryptJs.decrypt(str);
            return decrypted;
        };
    }

    exports("encryption", encryption);
});
