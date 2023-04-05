function WriteUtil() {
    var own = this;
    own.isWord = function (text) {
        var r = /<\w[^>]*(( class="?MsoNormal"?)|(="mso-))/gi;
        return r.test(text);
    }
    own.hasHtml = function (text) {
        var reg = /<[^>]+>/g;
        return reg.test(text);
    }
    own.isOffice = function (text) {
        var isOfficeDoc = false;
        if (BaseUtil.isNotEmpty(text)) {
            var document = new DOMParser().parseFromString(text, 'text/html');
            var es = document.getElementsByTagName('html');
            if (es) {
                var elementLength = es.length;
                for (var h = 0; h < elementLength; h++) {
                    var node = es[h];
                    if (node) {
                        var attributeMap = node.attributes;
                        var mapLength = attributeMap.length;
                        var isWord = false;
                        var isExcel = false;
                        var isOffice = false;

                        for (var j = 0; j < mapLength; j++) {
                            var attributeItem = attributeMap.item(j);
                            if (attributeItem && attributeItem.value) {
                                var attributeValue = attributeItem.value;
                                var hasWord = attributeValue.match(/:word/i);
                                var hasExcel = attributeValue.match(/:excel/i);
                                var hasOffice = attributeValue.match(/:office/i);

                                if (hasWord) {
                                    isWord = true;
                                }
                                if (hasExcel) {
                                    isExcel = true;
                                }
                                if (hasOffice) {
                                    isOffice = true;
                                }
                            }
                        }

                        isOfficeDoc = isWord || isExcel || isOffice;
                    }
                }
                // for (var j = 0; j < elementLength; j++) {
                //     var item = es[j];
                //     if (item) {
                //         item.attributes;
                //     }
                // }
            }
        }
        return own.isWord(text) || isOfficeDoc;
    }

    own.pasteHandle = function (
        e,
        htmlBack,
        imageBack,
        fileBack) {

        // Prevent the default pasting event and stop bubbling
        e.preventDefault();
        e.stopPropagation();

        // Get the clipboard data
        var html = '';
        var text = '';
        var clipboardData = (window.clipboardData || e.clipboardData);
        if (clipboardData) {
            html = (clipboardData).getData('text/html');
            text = (clipboardData).getData('text/plain');

            var items = clipboardData.items;

            var length = items.length;
            var hasText = (BaseUtil.isNotEmpty(html)) && (BaseUtil.isNotEmpty(text));
            if (length === 1 && !hasText) {
                var item = items[0];
                if (item.kind === 'file') {
                    var file = item.getAsFile();
                    if (file) {
                        if (item.type.match(/^image\//i)) {
                            if (typeof imageBack === 'function') {
                                imageBack(file);
                            }
                        } else {
                            // 文件上传
                            if (typeof fileBack === 'function') {
                                var files = [];
                                files.push(file);
                                fileBack(files);
                            }
                        }
                    }
                    return;
                }
            } else {
                if (typeof fileBack === 'function') {
                    var files = [];
                    for (var i = 0; i < length; i++) {
                        var item = items[i];
                        if (item.kind === 'file') {
                            var file = item.getAsFile();
                            if (file) {
                                files.push(file);
                            }
                        }
                    }
                    if (files.length > 0) {
                        fileBack(files);
                    }
                }
            }
        }
        // var isOfficeDoc = own.isOffice(html);

        var useHtml = BaseUtil.isNotEmpty(html);
        if (useHtml) {
            // if (isOfficeDoc) {
            //   useHtml = false;
            // }
        }
        if (useHtml) {
            // .replace(/<br([^<>]+|\s?)>/ig,‘||||‘);//替换br标签
            html = html.replace(/(<bR\/>|<bR>|<Br\/>|<Br>|<br\/>|<br>|<BR>|<BR\/>)/g, '\n');
            html = html.replace(/<(?!(img|IMG))[^>]*>/ig, '');
            html = BaseUtil.trim(html);
        } else {
            html = text;
        }
        if (html !== '') {
            if (typeof htmlBack === 'function') {
                htmlBack(html);
            }
        }
    }

    own.getCursorLocation = function () {
        var data = {x: 0, y: 0, text: '', node: null, range: null};
        var selection = window.getSelection();
        var createRange = document.createRange();
        var rangeCount = (selection) ? selection.rangeCount : 0;
        if (selection && rangeCount > 0 && selection.getRangeAt) {
            var range = selection.getRangeAt(0);// .cloneRange();
            if (range.getClientRects) {
                range.collapse(true);
                var rect;
                var rects = range.getClientRects();
                if (rects && rects.length > 0) {
                    rect = rects[0];
                }
                // 光标在行首时，rect为undefined
                if (rect) {
                    data.x = rect.left;
                    data.y = rect.top;
                }
            }
            // if (range.endContainer) {
            //     if (range.endContainer.nodeValue) {
            //         data.text = range.endContainer.nodeValue;
            //     }
            // }
            if (range.startContainer) {
                if (range.startContainer.nodeValue) {
                    data.text = range.startContainer.nodeValue;
                }
                data.node = range.startContainer;
            }
            data.range = range;
        }
        return data;
    }
}

var writeUtil = new WriteUtil();


function WriteView() {
    var own = this;

    own.insertBeforeHtml = function (html) {
        var element = document.createElement('div');
        element.innerHTML = html;
        document.body.insertBefore(element, document.body.firstElementChild);
    }

    own.insertLastHtml = function (html) {
        var element = document.createElement('div');
        element.innerHTML = html;
        document.body.appendChild(element);
    }

    own.replaceElementHtml = function (id, html) {
        var newElement = document.createElement('div');
        newElement.innerHTML = html;
        var oldElement = document.getElementById(id);
        document.body.replaceChild(newElement, oldElement);
    }

    own.setBodyHtml = function (html) {
        document.body.innerHTML = html;
    }

    own.insertAtCursorHtmlBck = function (html) {
        var selection = window.getSelection();
        var createRange = document.createRange();
        var rangeCount = (selection) ? selection.rangeCount : 0;
        if (selection && rangeCount > 0 && selection.getRangeAt) {
            var range = selection.getRangeAt(0);
            if (!range) {
                range = createRange;
            }
            if (range) {
                var element = document.createElement('p');
                element.innerHTML = html;
                // var nodes = element.childNodes;
                // var l = nodes.length;
                var list = [];
                var nodeLength = element.childNodes.length;
                for (var j = 0; j < nodeLength; j++) {
                    var n = element.childNodes[j];
                    // list.splice(0, 0, n);
                    list.push(n);
                }
                var node;
                var l = list.length;
                /*     for (var i = l - 1; i >= 0; i--) {
                         node = list[i];
                         if (node) {
                             range.insertNode(node);
                         }
                     }*/

                for (var i = 0; i < l; i++) {
                    node = list[i];
                    if (node) {
                        range.insertNode(node);
                    }
                }
                if (node) {
                    range.setStartAfter(node);
                    range.collapse(true);
                    // selection.removeAllRanges();
                    // selection.addRange(range);
                }
            }
        }
    }

    own.insertAtCursorHtml = function (html) {
        var selection = window.getSelection();
        var createRange = document.createRange();
        var rangeCount = (selection) ? selection.rangeCount : 0;
        if (selection && rangeCount > 0 && selection.getRangeAt) {
            var range = selection.getRangeAt(0);
            if (!range) {
                range = createRange;
            }
            if (range) {
                var element = document.createElement('p');
                element.innerHTML = html;

                var list = [];
                var nodeLength = element.childNodes.length;
                for (var j = 0; j < nodeLength; j++) {
                    var n = element.childNodes[j];
                    list.push(n);
                }
                var node;
                var l = list.length;
                for (var i = 0; i < l; i++) {
                    node = list[i];
                    if (node) {
						// appEditBridge.showLog(node.innerHTML);
                        range.insertNode(node);
                        range.setEndAfter(node);
                        range.setStartAfter(node);
                    }
                }
                if (node) {
                    // range.setEndAfter(node);
                    // range.setStartAfter(node);
					selection.removeAllRanges();
					selection.addRange(range);
                }
            }
        }
    }

    own.focus = function () {
        if (document.body !== document.activeElement) {
            document.body.focus();
        }
    }

    own.isFocus = function () {
        var is = false
        if (document.body !== document.activeElement) {
            is = true;
        }
        return is;
    }

    own.hasElement = function (id) {
        var has = false;
        var e = document.getElementById(id);
        if (e) {
            has = true;
        }
        return has;
    }

    own.replaceImageSrc = function (id, src) {
        var e = document.getElementById(id);
        if (e) {
            e.src = src;
        }
    }

    own.deleteSelection = function () {
        window.getSelection().deleteFromDocument();
    }

    own.toCursor = function (node) {
        var selection = window.getSelection();
        var createRange = document.createRange();
        var rangeCount = (selection) ? selection.rangeCount : 0;
        if (selection && rangeCount > 0 && selection.getRangeAt) {
            var range = selection.getRangeAt(0);
            if (!range) {
                range = createRange;
            }
            if (range) {
                if (node) {
                    range.setEndAfter(node);
                    range.setStartAfter(node);
					selection.removeAllRanges();
					selection.addRange(range);
                }
            }
        }
    }
}

var writeView = new WriteView();


function ContentGetUtil() {
    var own = this;
    var TYPE_TEXT = 'text';
    var TYPE_CODE = 'code';
    var TYPE_HTML = 'html';

    var TYPE_FILE = 'file';
    var TYPE_IMAGE = 'image';
    var TYPE_AUDIO = 'audio';
    var TYPE_VIDEO = 'video';

    var TYPE_FACE = 'face';
    var TYPE_URL = 'url';
    var TYPE_POSITION = 'position';
    var TYPE_AT = 'at';

    own.getContent = function (nodes) {
        var content;
        if (nodes && nodes.length > 0) {
            content = {};
            content.sections = [];
            content.key = BaseUtil.getKey();

            var section = {};
            section.items = [];

            content.sections.push(section);
            var length = nodes.length;
            for (var i = 0; i < length; i++) {
                var e = nodes[i];
                var nodeName = e.nodeName;
                nodeName = (nodeName) ? nodeName.toLowerCase() : '';
                if (nodeName === '#text') {
                    var value = e.nodeValue;
                    if ('\n' === value) {
                        section = {};
                        section.items = [];

                        content.sections.push(section);
                    } else if (value) {

                        var item = own.getTextItem(value);
                        if (item) {
                            section.items.push(item);
                        }
                    }
                }
                if ('br' === nodeName) {

                    section = {};
                    section.items = [];
                    content.sections.push(section);
                }

                if (nodeName === 'img') {
                    var url = e.src;
                    var name = e.name;
                    var value = e.getAttribute('value'); // .value;
                    var title = e.getAttribute('title');
                    var path = e.getAttribute('path');

                    if ('face' === (name)) {
                        var item = own.getFaceItem(e);
                        if (item) {
                            section.items.push(item);
                        }
                    } else if ('at' === (name)) {
                        var item = own.getAtItem(e);
                        if (item) {
                            section.items.push(item);
                        }
                    } else {
                        var item = own.getImageItem(e);
                        if (item) {
                            section.items.push(item);
                        }
                    }
                }

                if (nodeName === 'a') {
                    var name = e.name;
                    if ('at' === (name)) {
                        var item = own.getAtItem(e);
                        if (item) {
                            section.items.push(item);
                        }
                    }
                }

                if (nodeName === 'pre') {
                    var value = e.innerHTML;
                    var item = own.getTextItem(value);
                    if (item) {
                        section.items.push(item);
                    }
                }

                if (nodeName === 'div') {
                    section = own.getSection(e);
                    content.sections.push(section);
                }
            }
        }
        return content;
    }

    own.getSection = function (element) {
        var section = {};
        section.items = [];

        var nodes = element.childNodes;

        if (nodes) {
            var length = nodes.length;
            for (var i = 0; i < length; i++) {
                var e = nodes[i];
                var nodeName = e.nodeName;
                nodeName = (nodeName) ? nodeName.toLowerCase() : '';
                if (nodeName === '#text') {
                    var value = e.nodeValue;

                    var item = own.getTextItem(value);
                    if (item) {
                        section.items.push(item);
                    }
                }

                if (nodeName === 'img') {
                    var n = e;

                    var url = n.src;
                    var name = n.name;
                    var value = n.value;
                    var title = n.getAttribute('title');

                    if ('face' === (name)) {
                        var item = own.getFaceItem(e);
                        if (item) {
                            section.items.push(item);
                        }
                    } else if ('at' === (name)) {
                        var item = own.getAtItem(e);
                        if (item) {
                            section.items.push(item);
                        }
                    } else {
                        var item = own.getImageItem(e);
                        if (item) {
                            section.items.push(item);
                        }
                    }
                }
                if (nodeName === 'a') {
                    var n = e;
                    var name = n.name;
                    if ('at' === (name)) {
                        var item = own.getAtItem(e);
                        if (item) {
                            section.items.push(item);
                        }
                    }
                }
            }
        }
        return section;
    }

    own.getTextItem = function (text) {
        var item;
        if (text) {
            item = {};
            item.type = TYPE_TEXT;
            item.value = (text) ? text : '';
            item.key = BaseUtil.getKey();
        }
        return item;
    }

    own.getFaceItem = function (e) {
        var item;

        if (e) {
            var name = e.name;
            var value = e.getAttribute('value'); // .value;
            var title = e.getAttribute('title');
            var path = e.getAttribute('path');

            if (value) {
                var isNetFace = false;
                if (path) {
                    var u = path.toString().toLowerCase();
                    isNetFace = ((u.startsWith('http://') || u.startsWith('https://')));
                }

                var array = value.split(',');
                if (array.length > 1) {
                    var iv = {};
                    iv.categoryId = array[0];
                    iv.key = array[1];
                    iv.path = (isNetFace) ? path : '';
                    iv.text = title;

                    item = {};
                    item.type = TYPE_FACE;
                    item.value = iv;
                    item.key = BaseUtil.getKey();
                }
            }
        }
        return item;
    }

    own.getImageItem = function (e) {
        var item;

        if (e) {
            var url = e.src;
            var name = e.name;
            var value = e.getAttribute('value'); // .value;
            var title = e.getAttribute('title');
            var path = e.getAttribute('path');

            if (url) {
                var iv = {};
                if (value) {
                    if (BaseUtil.isJson(value)) {
                        iv = BaseUtil.jsonToObject(value);
                    }
                }
                iv.url = (url) ? url : '';

                item = {};
                item.type = TYPE_IMAGE;
                item.value = iv;
                item.key = BaseUtil.getKey();
            }
        }
        return item;
    }

    own.getAtItem = function (e) {
        var item;

        if (e) {
            var url = e.src;
            var name = e.name;
            var value = e.getAttribute('value');
            var text = e.getAttribute('text');

            if (value || value === '') {
                var iv = {};
                iv.userId = value;
                iv.text = text;

                item = {};
                item.type = TYPE_AT;
                item.value = iv;
                item.key = BaseUtil.getKey();
            }
        }
        return item;
    }
}

var contentGetUtil = new ContentGetUtil();

