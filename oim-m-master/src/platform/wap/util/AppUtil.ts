import appObject from '@/app/App';
import platformInitialize from '@/platform/initialize/PlatformInitialize';
import RouterUtil from '@/common/vue/RouterUtil';


class AppUtil {

    public static logout(): void {
        RouterUtil.toByPath('/login');
        appObject.logout();
        platformInitialize.initialize();
    }
}

export default AppUtil;
