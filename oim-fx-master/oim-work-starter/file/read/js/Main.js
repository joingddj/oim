function ReadView() {
    var own = this;

    own.insertBeforeHtml = function (html) {
        const element = document.createElement('div');
        element.innerHTML = html;
        document.body.insertBefore(element, document.body.firstElementChild);
    }

    own.insertLastHtml = function (html) {
        const element = document.createElement('div');
        element.innerHTML = html;
        document.body.appendChild(element);
    }

    own.replaceElementHtml = function (id, html) {
        const newElement = document.createElement('div');
        newElement.innerHTML = html;
        const oldElement = document.getElementById(id);
        document.body.replaceChild(newElement, oldElement);
    }

    own.setBodyHtml = function (html) {
        document.body.innerHTML = html;
    }

    own.checkElementSize = function (max) {
        if (!max) {
            max = 500;
        }
        const nodes = document.body.children;
        const length = nodes.length;
        if (length > max) {
            const size = length - max;
            for (let i = 0; i < size; i++) {
                document.body.removeChild(document.body.firstElementChild);
            }
        }
    }

    own.hasElement = function (id) {
        let has = false;
        const e = document.getElementById(id);
        if (e) {
            has = true;
        }
        return has;
    }


    own.replaceImageSrc = function (id, src) {
        const e = document.getElementById(id);
        if (e) {
            e.src = src;
        }
    }

    own.getScrollPosition = function () {
        const body = document.body;
        const doc = document.documentElement;

        const height = body.scrollHeight;
        const top = body.scrollTop;

        const clientHeight = body.clientHeight;
        let position = "";

        const a = (height - top);
        const b = (clientHeight + 10);

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
        const body = document.body;
        const height = body.scrollHeight;
        body.scrollTop = height;
    }
}

var readView = new ReadView();
window.webBridge = readView;

function insert() {
    alert('ddd');
    webBridge.insertLastHtml('<div>哈哈哈</div>');
}

