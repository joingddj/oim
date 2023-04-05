import Content from '@/app/com/common/chat/Content';
import Section from '@/app/com/common/chat/Section';
import Item from '@/app/com/common/chat/Item';

class ContentBuildUtil {
    public static getContentText(text: string): Content {
        let content: Content | any;
        if (text) {
            content = new Content();
            const section: Section = new Section();
            content.sections.push(section);
            const item: Item = new Item();
            item.type = Item.TYPE_TEXT;
            item.value = text;

            section.items.push(item);
        }
        return content;
    }
}

export default ContentBuildUtil;
