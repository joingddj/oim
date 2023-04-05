import Vue from 'vue';
import {Toast} from 'vant';
import InfoUtil from '@/app/base/message/util/InfoUtil';

Toast.allowMultiple(true);

export default class Prompt {

    public static message(info: any, successText: string, warningText: string) {
        let message = '';
        if (info) {
            if (info.success) {
                message = InfoUtil.getDefaultPromptText(info);
                if (!message || '' === message) {
                    message = successText;
                }
                if (message) {
                    Toast({
                        message,
                        icon: 'icon icon-success',
                    });
                }
            } else {
                message = InfoUtil.getDefaultErrorText(info);
                if (!message || '' === message) {
                    message = warningText;
                }
                if (message) {
                    Toast({
                        message,
                        icon: 'icon icon-warning',
                    });
                }
            }
        } else {
            if (warningText) {
                Toast({
                    message,
                    icon: 'icon icon-error',
                });
            }
        }
    }

    public static notice(message: string, title?: string, type?: string) {
        type = type ? type : 'info';

        if ('info' === type) {
            title = title || '信息';
            Toast(message);
            // Toast({
            //     message,
            //     icon: 'icon icon-info',
            // });
        }
        if ('success' === type) {
            title = title || '成功';
            Toast.success(message);
            // Toast({
            //     message,
            //     icon: 'icon icon-success',
            // });
        }
        if ('warn' === type) {
            title = title || '警告';
            Toast({
                message,
                icon: 'icon icon-warning',
            });
        }
        if ('error' === type) {
            title = title || '错误';
            Toast.fail(message);
            // Toast({
            //     message,
            //     icon: 'icon icon-error',
            // });
        }
    }

    public static info(message: string, title?: string) {
        title = title || '信息';
        Toast({
            message,
            icon: 'icon icon-info',
        });
    }

    public static success(message: string, title?: string) {
        title = title || '成功';
        Toast({
            message,
            icon: 'icon icon-success',
        });
    }

    public static warning(message: string, title?: string) {
        title = title || '警告';
        Toast({
            message,
            icon: 'icon icon-warning',
        });
    }

    public static error(message: string, title?: string) {
        title = title || '错误';
        Toast({
            message,
            icon: 'icon icon-error',
        });
    }
}
