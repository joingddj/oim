class BaseUtil {

    static isEmpty(value) {
        let empty = false;
        if (value instanceof Array) {
            empty = value.length <= 0;
        } else {
            empty = BaseUtil.trim(value) === '' || value === undefined || value === null || value === 'undefined' || value === 'null' || value === '&nbsp;';
        }
        return empty;
    }

    static isNotEmpty(value) {
        return !BaseUtil.isEmpty(value);
    }


    static getTimestamp() {
        const timestamp = new Date().getTime();
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
    static objectToJson(value) {
        if (BaseUtil.isEmpty(value)) {
            return '';
        }
        const json = JSON.stringify(value);
        return json;
    }

    /**
     * 将json字符串转成json对象
     * @param {type} json
     * @returns {undefined|Function}
     */
    static jsonToObject(json) {
        let value;
        if (!BaseUtil.isEmpty(json)) {
            try {
                value = (new Function('return ' + json))();
            } catch (e) {
                // do something
            }
        }
        return value;
    }

    static isJson(text) {
        if (typeof text === 'string') {
            try {
                const o = JSON.parse(text);
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

    static isJsonObject(text) {
        if (BaseUtil.isJson(text)) {
            if (text.indexOf('{') > -1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    static isJsonArray(text) {
        if (BaseUtil.isJson(text)) {
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

    static trim(text) {
        const exp = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
        return text === null ? '' : (text + '').replace(exp, '');
    }

    static moreFilter(text, size) {
        let value = '';
        if (BaseUtil.isEmpty(text)) {
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

    static moreUtf8Filter(text, size) {
        let value = '';
        if (BaseUtil.isEmpty(text)) {
            return value;
        }
        if (BaseUtil.lengthByUtf8(text) > size) {
            value = BaseUtil.substringByUtf8(text, 0, size);
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
    static lengthByUtf8(text) {
        let realLength = 0;
        if (text) {
            const length = text.length;
            let charCode = -1;
            for (let i = 0; i < length; i++) {
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
    static substringByUtf8(text, start, length) {
        // 定位开始位置
        let startUtf8 = 0;
        let charCode = -1;
        let i = 0;
        let end = start + length;
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
        let endUtf8 = 0;
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


    static addURLParameter(url, param) {
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

    static getClassName(clazz) {
        if (typeof clazz === 'string') {
            return clazz;
        }
        const key = 'function';
        let name = clazz.toString();
        if (name.indexOf(key) === -1) {
            return null;
        } else {
            name = name.replace(key, '');
            const index = name.indexOf('(');
            name = name.substring(0, index);
            name = name.replace(' ', '');
        }
        return name;
    }
}
