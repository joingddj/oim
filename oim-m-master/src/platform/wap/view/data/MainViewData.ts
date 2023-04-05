import MainTabData from '@/platform/wap/view/data/MainTabData';
import mainBaseTabs from '@/platform/wap/view/data/MainBaseTabs';

class MainViewData {
    public tab: string = mainBaseTabs.messageTab.key;
    public tabs: MainTabData[] = [];

    public initialize(): void {
        this.tabs = mainBaseTabs.tabs;
    }

    private add() {
        // no
    }
}

const mainViewData: MainViewData = new MainViewData();
export default mainViewData;
