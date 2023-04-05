import mainBaseTabs from '@/platform/wap/view/data/MainBaseTabs';

class MessageAllUnreadViewModel {
    public setRed(red: boolean, count: number): void {
        mainBaseTabs.messageTab.red = red;
        mainBaseTabs.messageTab.redCount = count;
    }
}

const messageAllUnreadViewModel: MessageAllUnreadViewModel = new MessageAllUnreadViewModel();
export default messageAllUnreadViewModel;
