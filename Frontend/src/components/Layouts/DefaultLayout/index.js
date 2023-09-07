import DefaultHeader from '~/components/Header/DefaultHeader';
import DefaultFooter from '~/components/Footer/DefaultFooter';

function DedaultLayout({ children }) {
    return (
        <div>
            <DefaultHeader />
            <div>{children}</div>
            <DefaultFooter />
        </div>
    );
}

export default DedaultLayout;
