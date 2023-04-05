import SessionCache from '@/app/common/cache/SessionCache';

class Auth {
    public isFirst: boolean = true;

    private cache: SessionCache = new SessionCache('authCache');
    private accountKey = 'auth.account';
    private passwordKey = 'auth.password';
    private loginKey = 'auth.login';
    private tokenKey = 'auth.token';
    private userIdKey = 'auth.userId';
    private login: boolean = false;


    public isLogin(): boolean {
        return this.login;
    }

    public isAuth(): boolean {
        const account = this.getAccount();
        const password = this.getPassword();
        const token = this.getToken();
        let isAuth = false;
        if (account && password && token) {
            isAuth = true;
        }
        return isAuth;
    }

    public setLogin(isLogin: boolean): void {
        this.login = isLogin;
        if (isLogin) {
            this.isFirst = false;
        }
    }

    public setToken(token: string): void {
        this.cache.set(this.tokenKey, token);
    }

    public getToken(): string {
        let token: any = this.cache.get(this.tokenKey);
        if (!token) {
            token = '';
        }
        return token;
    }


    public setUserId(userId: string): void {
        this.cache.set(this.userIdKey, userId);
    }

    public getUserId(): string {
        let userId: any = this.cache.get(this.userIdKey);
        if (!userId) {
            userId = '';
        }
        return userId;
    }

    public setAccount(account: string): void {
        this.cache.set(this.accountKey, account);
    }

    public getAccount(): string {
        let v: any = this.cache.get(this.accountKey);
        if (!v) {
            v = '';
        }
        return v;
    }


    public setPassword(password: string): void {
        this.cache.set(this.passwordKey, password);
    }

    public getPassword(): string {
        let v: any = this.cache.get(this.passwordKey);
        if (!v) {
            v = '';
        }
        return v;
    }


    public removeToken(): void {
        this.cache.delete(this.tokenKey);
    }

    public removeUserId(): void {
        this.cache.delete(this.userIdKey);
    }

    public removeAccount(): void {
        this.cache.delete(this.accountKey);
    }

    public removePassword(): void {
        this.cache.delete(this.passwordKey);
    }

    public removeLogin(): void {
        this.cache.delete(this.loginKey);
    }

    public clear(): void {
        // this.removeLogin();
        this.setLogin(false);
        this.removeUserId();
        this.removeToken();
        this.removeAccount();
        this.removePassword();
    }
}

export default new Auth();
