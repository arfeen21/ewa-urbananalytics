import { UserModelComponent } from '../user/user.model';

export class NotificationMessage {

    message: String;
    from: UserModelComponent;
    to: UserModelComponent;
    isread: boolean;
    hrefUrl: String;

    public constructor(message: String, from: UserModelComponent, to: UserModelComponent, isRead: boolean, hrefUrl: String = "") {
        this.message = message;
        this.from = from;
        this.to = to;
        this.hrefUrl = hrefUrl;
        this.isread = isRead;
    }


}