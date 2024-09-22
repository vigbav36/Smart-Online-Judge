import {Layout} from 'antd';
const { Header} = Layout;
const PageHeader = () => {
    return (
        <Header
            style={{
            position: 'sticky',
            top: 0,
            zIndex: 1,
            width: '100%',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'space-between',
            padding: '0 20px',
            }}
        >
        <div className="demo-logo" />
        <h1 style={{ fontSize: '1.3rem', margin: 0, color: 'white' }}>Smart Judge - Making interview prep smart</h1>
        </Header>
    );
  };
  
  export default PageHeader;