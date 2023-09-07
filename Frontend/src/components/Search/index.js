import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { useSelector } from 'react-redux';

import styles from './search.scss';
import classNames from 'classnames';

const cx = classNames.bind(styles);

function Search() {
    
    const context = useSelector((state) => state.Language);
    return (
        <div className={cx('wrapper')}>
            <form className={cx('form')}>
                <input type="text" placeholder={context.search} id="search"></input>
                <button
                    type="button"
                    className={cx('search')}
                    onClick={()=>{window.location.href = "/search"}}
                >
                    <FontAwesomeIcon icon={faMagnifyingGlass} />
                </button>
            </form>
        </div>
    );
}

export default Search;
