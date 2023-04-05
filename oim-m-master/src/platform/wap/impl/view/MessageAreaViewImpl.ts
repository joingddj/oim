import MessageAreaView from '@/app/com/main/view/MessageAreaView';
import AbstractMaterial from '@/app/base/context/AbstractMaterial';

class MessageAreaViewImpl extends AbstractMaterial implements MessageAreaView {
    private type: string = 'no';

    public showType(type: string): void {
        this.type = type;
    }

    public getType(): string {
        return this.type;
    }
}

export default MessageAreaViewImpl;
