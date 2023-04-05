import MainTabData from '@/platform/wap/view/data/MainTabData';

class MainBaseTabs {
    public tabs: MainTabData[] = [];
    public messageTab: MainTabData = new MainTabData();
    public contactTab: MainTabData = new MainTabData();
    public moduleTab: MainTabData = new MainTabData();
    public personalTab: MainTabData = new MainTabData();

    constructor() {
        this.initialize();
    }

    public initialize(): void {
        const own = this;
        const tabs = this.tabs;
        tabs.length = 0;
        let data: MainTabData = this.messageTab;
        data.key = 'message_tab';
        data.title = '消息';
        data.icon = 'chat';
        data.setOnSelected(() => {
            // no
        });
        tabs.push(data);

        data = this.contactTab;
        data.key = 'contact_tab';
        data.title = '通讯录';
        data.icon = 'group';
        data.setOnSelected(() => {
            // no
        });
        tabs.push(data);

        data = this.personalTab;
        data.key = 'personal_tab';
        data.title = '我';
        data.icon = 'person';
        data.setOnSelected(() => {
            // no
        });
        tabs.push(data);
        //
        // data = this.moduleTab;
        // data.key = 'module_tab';
        // data.icon = 'fas fa-th-large';
        // data.setOnSelected(() => {
        //     // no
        // });
        // tabs.push(data);
    }
}

const mainBaseTabs = new MainBaseTabs();
export default mainBaseTabs;
