import styles from './AuthorCard.scss';
import classNames from 'classnames/bind';
import { useSelector } from 'react-redux';

const cx = classNames.bind(styles);

function AuthorCard({ name, type, img, width }) {
    const context = useSelector((state)=>state.Language)
    let wrapperClass = cx('AuthorCard_wrapper_outer');
    if (width) {
        wrapperClass = cx('AuthorCard_wrapper_outer', 'toWidth');
    }
    return (
        <div className={wrapperClass}>
            <div className={cx('AuthorCard_wrapper_inner')}>
                <div className={cx('AuthorCard_img')}>
                    <img src={img} alt={name} />
                </div>
                <div className={cx('AuthorCard_title')}>{name}</div>

                <div className={cx('AuthorCard_infor')}>
                    <div className={cx('Infor_title')}>{context.type}</div>
                    <div className={cx('Infor_content')}>{type}</div>
                </div>
            </div>
        </div>
    );
}

export default AuthorCard;
