function Util() {
    var own = this;
	var count=0;
    own.isEmpty = function (value) {
        var empty = false;
        if (value instanceof Array) {
            empty = value.length <= 0;
        } else {
            empty = own.trim(value) === '' || value === undefined || value === null || value === 'undefined' || value === 'null' || value === '&nbsp;';
        }
        return empty;
    }

    own.isNotEmpty = function (value) {
        return !own.isEmpty(value);
    }


    own.getTimestamp = function () {
        var timestamp = new Date().getTime();
        return timestamp;
    }

    /******************************************************************************
     * json和对象相关方法start                                                      *
     ******************************************************************************/

    /**
     * 对象转成json字符串
     * @param {type} value
     * @returns {String}
     */
    own.objectToJson = function (value) {
        if (own.isEmpty(value)) {
            return '';
        }
        var json = JSON.stringify(value);
        return json;
    }

    /**
     * 将json字符串转成json对象
     * @param {type} json
     * @returns {undefined|Function}
     */
    own.jsonToObject = function (json) {
        var value;
        if (!own.isEmpty(json)) {
            try {
                value = (new Function('return ' + json))();
            } catch (e) {
                // do something
            }
        }
        return value;
    }

    own.isJson = function (text) {
        if (typeof text === 'string') {
            try {
                var o = JSON.parse(text);
                if (text.indexOf('{') > -1 || text.indexOf('[') > -1) {
                    return true;
                } else {
                    return false;
                }
            } catch (e) {
                return false;
            }
        }
        return false;
    }

    own.isJsonObject = function (text) {
        if (own.isJson(text)) {
            if (text.indexOf('{') > -1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    own.isJsonArray = function (text) {
        if (own.isJson(text)) {
            if (text.indexOf('[') > -1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /******************************************************************************
     * json和对象相关方法end                                                       *
     ******************************************************************************/


    /******************************************************************************
     * String 相关方法end                                                       *
     ******************************************************************************/

    own.trim = function (text) {
        var exp = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
        return text === null ? '' : (text + '').replace(exp, '');
    }

    own.moreFilter = function (text, size) {
        var value = '';
        if (own.isEmpty(text)) {
            return value;
        }
        if (text.length > size) {
            value = text.slice(0, size);
            value += '...';
            return value;
        } else {
            return text;
        }
    }

    own.moreUtf8Filter = function (text, size) {
        var value = '';
        if (own.isEmpty(text)) {
            return value;
        }
        if (own.lengthByUtf8(text) > size) {
            value = own.substringByUtf8(text, 0, size);
            value += '...';
            return value;
        } else {
            return text;
        }
    }

    /**
     * 获取字符utf8长度，中文按2字节算
     * @param text
     * @returns {Number}
     */
    own.lengthByUtf8 = function (text) {
        var realLength = 0;
        if (text) {
            var length = text.length;
            var charCode = -1;
            for (var i = 0; i < length; i++) {
                charCode = text.charCodeAt(i);
                if (charCode >= 0 && charCode <= 128) {
                    realLength += 1;
                } else {
                    realLength += 2;
                }
            }
        }
        return realLength;
    }

    /**
     * 按utf8格式截取字符，英文数字按1字符算，汉字按2算
     * @param text 原字符
     * @param start 开始位置
     * @param length 截字数
     */
    own.substringByUtf8 = function (text, start, length) {
        // 定位开始位置
        var startUtf8 = 0;
        var charCode = -1;
        var i = 0;
        var end = start + length;
        while (start > 0) {
            charCode = text.charCodeAt(i);
            if (charCode >= 0 && charCode <= 128) {// 健盘字符，每次-1
                start -= 1;
            } else {// 非健盘字符，每次-2
                start -= 2;
            }
            i++;
            startUtf8++;
        }

        // 定位给束位置
        var endUtf8 = 0;
        while (end > start) {
            charCode = text.charCodeAt(i);
            if (charCode >= 0 && charCode <= 128) {// 健盘字符，每次-1
                end -= 1;
            } else {// 非健盘字符，每次-2
                end -= 2;
            }
            i++;
            endUtf8++;
        }
        return text.substring(startUtf8, endUtf8);
    }


    /******************************************************************************
     * String 相关方法end                                                       *
     ******************************************************************************/


    own.addUrlParameter = function (url, param) {
        if (param && url) {
            if (url.indexOf('?') === -1) {
                url += '?';
            } else {
                url += '&';
            }
            url += param;
            url = url.replace('&&', '&');
            url = url.replace('??', '?');
            url = url.replace('?&', '?');
        }
        return url;
    }

    own.getClassName = function (clazz) {
        if (typeof clazz === 'string') {
            return clazz;
        }
        var key = 'function';
        var name = clazz.toString();
        if (name.indexOf(key) === -1) {
            return null;
        } else {
            name = name.replace(key, '');
            var index = name.indexOf('(');
            name = name.substring(0, index);
            name = name.replace(' ', '');
        }
        return name;
    }

    own.getUuidFull = function () {
        var d = new Date().getTime();
        if (window.performance && typeof window.performance.now === 'function') {
            d += performance.now(); // use high-precision timer if available
        }
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            var r = (d + Math.random() * 16) % 16 | 0;
            d = Math.floor(d / 16);
            return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
        });
    }

    own.getUuidCode = function () {
        var uuld = own.getUuidFull();
        return uuld.replace(/(-)/g, '');
    }

    own.getUuidCodeLower = function () {
        var uuld = own.getUuidCode();
        return uuld.toLowerCase();
    }

	own.getUuidKey = function () {
        var uuld = own.getUuidCodeLower();
        return uuld;
    }

    own.getKey = function () {
		count++;
		var time = new Date().getTime();
        return time + count;
    }

	own.init = function () {
		if (typeof String.prototype.startsWith != 'function') {
			String.prototype.startsWith = function (prefix){
				return this.slice(0, prefix.length) === prefix;
			};
		}

		if (typeof String.prototype.endsWith != 'function') {
			String.prototype.endsWith = function(suffix) {
				return this.indexOf(suffix, this.length - suffix.length) !== -1;
			};
		}
    }

	own.init();
}

var BaseUtil = new Util();
