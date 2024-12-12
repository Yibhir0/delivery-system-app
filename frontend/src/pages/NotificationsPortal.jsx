import {useEffect, useState} from "react";
import {getNotificationsByUserId} from "../api/get/GetDataService.js";
import "../styles/notifications-portal.css";
import {markNotificationAsRead, markNotificationAsUnRead} from "../api/post/PostDataService.js";
import {useNavigate} from "react-router-dom";

const NotificationsPortal = () => {
    const [notifications, setNotifications] = useState([]);
    const navigate = useNavigate();


    useEffect(() => {
        const savedUser = JSON.parse(localStorage.getItem("user"));
        const { id } = savedUser;
        const fetchNotifications = async () => {
            const data = await getNotificationsByUserId(id);
            setNotifications(data);
        };
        fetchNotifications();
    }, []);

    const showMarkAsRead = (id, read) => {
        document.getElementById(`view-${id}`).style.display = 'inline-block';
        if (read) {
            document.getElementById(`mark-as-unread-${id}`).style.display = 'inline-block';
        } else {
            document.getElementById(`mark-as-read-${id}`).style.display = 'inline-block';
        }
    };

    const hideMarkAsRead = (id,read) => {
        document.getElementById(`view-${id}`).style.display = 'none';
        if (read) {
            document.getElementById(`mark-as-unread-${id}`).style.display = 'none';
        }else{
            document.getElementById(`mark-as-read-${id}`).style.display = 'none';
        }
    };

    const markAsRead = (id) => {
        markNotificationAsRead(id).then(r => {
            setNotifications(notifications.map(n => n.id === id ? {...n, read: true} : n));
        });
    }

    function markAsUnread(id) {
        markNotificationAsUnRead(id).then(r => {
            setNotifications(notifications.map(n => n.id === id ? {...n, read: false} : n));
        });

    }

    function markAllAsRead() {
        notifications.forEach(notification => {
            if (!notification.read) {
                markNotificationAsRead(notification.id);
            }
        });
        setNotifications(notifications.map(n => ({...n, read: true})));
    }

    function goToDelivery(deliveryRequest) {
        const id = deliveryRequest;
        navigate('/package-detail', { state: { id } });
    }

    return (
        <div className="notifications-container">
            <h2>Notifications</h2>
            <hr/>
            <button
                style={{float: 'right', border: 'none', background: 'none', color: '#007bff', cursor: 'pointer'}}
                onClick={() => markAllAsRead()}
            >
                Mark all as read &#x2713;
            </button>
            <br/>
            <ul className="notifications-list">
                {notifications.sort((a, b) => a.read - b.read).map((notification) => (
                    <li
                        key={notification.id}
                        id={`notification-${notification.id}`}
                        className={`notification-item ${notification.read ? 'read' : 'unread'}`}
                        onMouseEnter={() => showMarkAsRead(notification.id, notification.read)}
                        onMouseLeave={() => hideMarkAsRead(notification.id, notification.read)}
                    >
                        {notification.message}
                        <a href="#" id={`view-${notification.id}`} className="notification-actions"
                            onClick={()=>{goToDelivery(notification.deliveryRequest)}}>
                            View delivery 📦
                        </a>
                        {notification.read ? (
                            <a href="#" id={`mark-as-unread-${notification.id}`} className="notification-actions"
                               onClick={() => markAsUnread(notification.id)}>
                                Mark as unread &#x2709;
                            </a>
                        ) : (
                            <a href="#" id={`mark-as-read-${notification.id}`} className="notification-actions"
                               onClick={() => markAsRead(notification.id)}>
                                Mark as read &#x1F4E8;
                            </a>
                        )}
                    </li>
                ))}
            </ul>
        </div>
    );

}

export default NotificationsPortal;