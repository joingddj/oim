function AppBridge() {
    var own = this;

    own.download = function (messageKey, itemKey, url, name, size) {
        // nativeBridge.onScrollTop();
        // own.invoke('download', messageKey, itemKey);
        var bridge = window.nativeBridge;
        if (bridge) {
            bridge.download(messageKey, itemKey, url, name, size);
        }
    }

    own.showImage = function (messageKey, itemKey, url) {
        // own.invoke('showImage', messageKey, itemKey);
        var bridge = window.nativeBridge;
        if (bridge) {
            bridge.showImage(messageKey, itemKey, url);
        }
    }

	own.openUrl = function (url) {
        var bridge = window.nativeBridge;
        if (bridge) {
            bridge.openUrl(url);
        }
    }
    
    own.resend = function (key) {
        // own.invoke('resend', key);
        var bridge = window.nativeBridge;
        if (bridge) {
            bridge.resend(key);
        }
    }

    own.saveScrollTop = function () {
        var top = readView.getScrollTop();
        // own.invoke('saveScrollTop', top);
        var bridge = window.nativeBridge;
        if (bridge) {
            bridge.saveScrollTop(top);
        }
    }

    own.onScrollTop = function (elementSize) {
        var bridge = window.nativeBridge;
        if (bridge) {
            bridge.onScrollTop(elementSize);
        }
    }

    // own.invoke = function (methodName, ...args) {
    //
    //     var o;
    //     var bridge = window.nativeBridge;
    //     if (bridge) {
    //         bridge.saveScrollTop(12);
    //         var m = bridge[methodName];
    //         if (typeof m === 'function') {
    //             o = m(args);
    //         }
    //     }
    //     return o;
    // }
}
