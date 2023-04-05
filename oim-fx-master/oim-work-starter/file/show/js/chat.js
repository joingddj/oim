function ReadView() {
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

    own.checkElementSize = function (max) {
        if (!max) {
            max = 500;
        }
        var nodes = document.body.children;
        var length = nodes.length;
        if (length > max) {
            var size = length - max;
            for (var i = 0; i < size; i++) {
                document.body.removeChild(document.body.firstElementChild);
            }
        }
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

    own.getScrollPosition = function () {
        var body = document.body;
        var doc = document.documentElement;

        var height = body.scrollHeight;
        var top = body.scrollTop;

        var clientHeight = body.clientHeight;
        var position = "";

        var a = (height - top);
        var b = (clientHeight + 10);

        if (a < b) {
            position = "bottom";
        } else if (top === 0) {
            position = "top";
        } else {
            position = "middle";
        }
        return position;
    }

    own.scrollToBottom = function () {
        var body = document.body;
        var height = body.scrollHeight;
        body.scrollTop = height;
    }
}

var view = new ReadView();

