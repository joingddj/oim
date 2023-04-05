import path from 'path';
import {execFile} from 'child_process';

class WindowsScreenShot {

    private isDevelopment: boolean = process.env.NODE_ENV !== 'production';
    private basePath: string = path.join(__dirname, '/');

    public constructor() {
        // no
    }

    public shotTemp(back: (file: File) => void): void {
        const own = this;
        const openWindow = () => {
            let base = own.basePath;
            const exe = 'lib/electron/windows/screenshot/PrintScr.exe';
            // const exe = 'lib/electron/windows/screenshot/PrintScr.exe';
            if (own.isDevelopment) {
                base = path.join(__dirname, '../../../../../../public/');
            } else {
                base = path.join(__dirname, '../app/');
            }
            const v = __dirname;

            const url = path.join(base, exe);

            const screenWindow = execFile(url);
            // console.log(screenWindow)
            screenWindow.on('exit', (code: any) => {
                // no
            });
        };
        openWindow();
    }

    public shot(back: (file: File) => void) {
        const own = this;
        return new Promise((resolve) => {
            let base = own.basePath;
            const exe = 'lib/electron/windows/screenshot/PrintScr.exe';
            if (own.isDevelopment) {
                base = path.join(__dirname, '../../../../../../public/');
            } else {
                base = path.join(__dirname, '../app/');
            }
            const url = path.join(base, exe);
            const screenWindow = execFile(url);
            screenWindow.on('exit', (code: any) => {

                if (code !== 1) {
                    return;
                }

                const buffer = require('electron').clipboard.readImage().toPNG();
                // @ts-ignore
                // const imgData = new Buffer.from(buffer, 'base64');
                // const imgs = 'data:image/png;base64,' + btoa(new Uint8Array(imgData).reduce((data, byte) => data + String.fromCharCode(byte), ''));
                // resolve(imgs);
                const date: Date = new Date();

                const fileInfo = {
                    buffer, // use this Buffer instead of reading file
                    name: '1.png', // optional when using `path`
                    type: 'image/png',
                    lastModified: date,
                };
                const blob = new Blob([fileInfo.buffer], {
                    type: fileInfo.type,
                });
                const lastModified: number = (fileInfo.lastModified) ? fileInfo.lastModified.getTime() : 0;
                const fp = {
                    type: fileInfo.type,
                    lastModified,
                } as FilePropertyBag;


                const file = new File([blob], fileInfo.name, fp);
                resolve(file);
                back(file);
            });
        });
    }
}

export default new WindowsScreenShot();
