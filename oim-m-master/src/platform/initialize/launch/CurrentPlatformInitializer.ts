import app from '@/app/App';
import AppContext from '@/app/base/context/AppContext';
import Platform from '@/app/common/util/Platform';
import DefineExtendStore from '@/app/define/extend/DefineExtendStore';
import FileDownloadDefineData from '@/app/com/client/module/file/FileDownloadDefineData';
import LaunchInitializer from '@/app/base/initialize/LaunchInitializer';


export default class CurrentPlatformInitializer extends LaunchInitializer {

    public getOrder(): number {
        return 0;
    }

    public initialize(): void {
        this.initializeHandle(this.appContext);
    }

    public getKey(): string {
        const own: object = this;
        return own.constructor.name;
    }

    public initializeHandle(appContext: AppContext): void {
        this.initializeChat(appContext);
    }

    public initializeChat(appContext: AppContext) {
        // no
    }
}
