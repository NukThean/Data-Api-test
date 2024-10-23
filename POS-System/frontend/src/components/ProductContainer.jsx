import {
  Input,
  Form,
  Select,
  Grid,
  Col,
  Row,
  Upload,
  Button,
  Flex,
  Card,
  Empty,
  Spin,
} from "antd";
import { useState } from "react";

function ProductContainer({ datas, handleDelete, isLoading }) {
  const [toggleAdd, setToggleAdd] = useState();

  const options = [
    { value: "electronics", label: "Electronics" },
    { value: "fashion", label: "Fashion" },
    { value: "home", label: "Home & Living" },
    { value: "books", label: "Books", disabled: true },
    { value: "sports", label: "sports", disabled: true },
  ];

  const isActiveOptions = [
    { value: "true", label: "True" },
    { value: "false", label: "False" },
  ];

  function handleAdd() {
    setToggleAdd(!toggleAdd);
  }

  return (
    <Row>
      <Col span={12} style={{ padding: "40px", width: "100%" }}>
        <Flex vertical gap="large">
          <Flex justify="space-between" align="center">
            <h1>Product Lists</h1>

            <div className="action-buttons">
              <Button onClick={handleAdd}>Add</Button>
            </div>
          </Flex>

          {toggleAdd && (
            <Form>
              <Flex gap={30} vertical>
                <Row gutter={[16, 16]}>
                  <Col span={12}>
                    <Form.Item
                      label="Code"
                      name="productCode"
                      layout="vertical"
                      rules={[
                        { required: true, message: "Please input your code!" },
                      ]}
                    >
                      <Input allowClear autoComplete="off" />
                    </Form.Item>
                  </Col>

                  <Col span={12}>
                    <Form.Item
                      label="Name"
                      name="productName"
                      layout="vertical"
                      rules={[
                        { required: true, message: "Please input your code!" },
                      ]}
                    >
                      <Input allowClear autoComplete="off" />
                    </Form.Item>
                  </Col>

                  <Col span={12}>
                    <Form.Item
                      label="Quantity"
                      name="productQuantity"
                      layout="vertical"
                      rules={[
                        { required: true, message: "Please input your code!" },
                      ]}
                    >
                      <Input allowClear autoComplete="off" />
                    </Form.Item>
                  </Col>

                  <Col span={12}>
                    <Form.Item label="Category" required layout="vertical">
                      <Select options={options} />
                    </Form.Item>
                  </Col>

                  <Col span={24}>
                    <Form.Item
                      label="Description"
                      name="productDescription"
                      layout="vertical"
                      rules={[
                        { required: true, message: "Please input your code!" },
                      ]}
                    >
                      <Input.TextArea allowClear />
                    </Form.Item>
                  </Col>
                </Row>

                <Row gutter={[16, 16]}>
                  <Col span={12}>
                    <Form.Item label="Is Active" required layout="vertical">
                      <Select options={isActiveOptions} />
                    </Form.Item>
                  </Col>

                  <Col span={12}>
                    <Form.Item
                      label="Vendor Id"
                      name="vendorId"
                      layout="vertical"
                      rules={[
                        { required: true, message: "Please input your code!" },
                      ]}
                    >
                      <Input allowClear />
                    </Form.Item>
                  </Col>
                </Row>

                <Form.Item
                  label="Upload Image"
                  name="imageUrl"
                  layout="vertical"
                  rules={[
                    { required: true, message: "Please input your code!" },
                  ]}
                >
                  <Upload>
                    <Button>Click here to upload</Button>
                  </Upload>
                </Form.Item>

                <Button type="submit">Register</Button>
              </Flex>
            </Form>
          )}
        </Flex>
      </Col>

      <Col span={12}>
        <Flex vertical>
          <h2 className="header">Products</h2>
          {isLoading && <Spin />}
          {datas.length === 0 && !isLoading && <Empty />}
          {datas.length > 0 && !isLoading && (
            <Flex vertical gap="middle">
              {datas.map((d) => {
                return (
                  <Card key={d.id} style={{ width: "440px" }}>
                    <Flex justify="space-between">
                      <Flex vertical>
                        <div>{d.name}</div>
                        <div>{d.code}</div>
                      </Flex>

                      <Button
                        className="btn delete"
                        onClick={() => {
                          handleDelete(d.id);
                        }}
                      >
                        Delete
                      </Button>
                    </Flex>
                  </Card>
                );
              })}
            </Flex>
          )}
        </Flex>
      </Col>
    </Row>
  );
}

export default ProductContainer;
