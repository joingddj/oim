abstract class BaseRouteConfig {

    public abstract getTitle(): string;

    public abstract getKey(): string;

    public abstract getRoutes(): any[];
}

export default BaseRouteConfig;
