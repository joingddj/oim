<template>
    <div class="common-main-scroll-pane">
        <v-card
                class="mx-auto"
                elevation="8"
        >
            <v-list-item
                    @click=""
            >
                <back-button></back-button>
                <v-toolbar-title>{{ '群成员' }}</v-toolbar-title>
                <v-spacer></v-spacer>
            </v-list-item>
        </v-card>
        <div class="center">
            <v-list>
                <template v-for="(item, i)  of entity.users">
                    <v-card>
                        <v-list-item
                                :key="item.id"
                                @click=""
                        >
                            <v-avatar>
                                <v-img :src="item.avatar"></v-img>
                            </v-avatar>
                            <div style="width: 15px;"></div>
                            <v-list-item-content>
                                <v-list-item-title>{{ getNickname(item) }}</v-list-item-title>
                                <v-list-item-subtitle>{{ '账号：' }}{{ item.account }}</v-list-item-subtitle>
                            </v-list-item-content>
                        </v-list-item>
                    </v-card>
                </template>
            </v-list>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Inject, Model, Prop, Provide, Vue, Watch} from 'vue-property-decorator';
import BackButton from '@/views/module/common/BackButton.vue';
import User from '@/app/com/main/module/business/user/bean/User';
import GroupMemberListEntity from '@/views/module/group/member/GroupMemberListEntity';
import BaseUtil from '@/app/lib/util/BaseUtil';
import UserInfoUtil from '@/app/com/main/common/util/UserInfoUtil';


@Component({
    components: {
        BackButton,
    },
})
export default class GroupMemberListPane extends Vue {
    private entity: GroupMemberListEntity = new GroupMemberListEntity();
    private items: User[] = [];
    @Prop({
        type: String,
        required: false,
        default: () => (''),
    })
    private groupId!: string;

    public mounted() {
        // no
        this.loadList();
    }

    private loadList() {
        const groupId = this.groupId;
        const entity = this.entity;
        entity.initialize(groupId);
    }

    private getNickname(user: User): string {
        let nickname = '';
        if (user) {
            nickname = this.entity.getNickname(user.id);
            if (BaseUtil.isEmpty(nickname)) {
                nickname = UserInfoUtil.getShowName(user);
            }
        }
        return nickname;
    }
}
</script>

<style lang="scss">

</style>
