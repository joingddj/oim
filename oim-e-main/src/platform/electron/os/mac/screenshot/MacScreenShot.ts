import {exec} from 'child_process';

export default class MacScreenShot {
    public handleScreenShots() {
        exec(`screencapture -i -U -c`, (error, stdout, stderr) => {
            // console.log(error);
        });
    }
}
