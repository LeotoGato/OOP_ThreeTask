//Задание по переопределению методов equals() и hashCode() для классов Order, Product, Delivery и Client.
//Так же демонстрируется работа equals() и hashCode()

import java.util.Objects;

class Order {
    private int number;
    private Client client;

    public Order(int number, Client client) {
        this.number = number;
        this.client = client;
    }

    public int getNumber() {
        return number;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return number == order.number && Objects.equals(client, order.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, client);
    }
}

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(name, product.name) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, price);
    }
}

class Delivery {
    private Product product;
    private Order order;
    private String courier;

    public static String defaultShippingMethod = "Standard";
    public static int defaultDeliveryDays;

    static {
        defaultDeliveryDays = 3;
    }

    public Delivery(Product product, Order order, String courier) {
        this.product = product;
        this.order = order;
        this.courier = courier;
    }

    public Product getProduct() {
        return product;
    }

    public Order getOrder() {
        return order;
    }

    public String getCourier() {
        return courier;
    }

    public static void setDefaultDeliveryDays(int days) {
        defaultDeliveryDays = days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(product, delivery.product) && Objects.equals(order, delivery.order) && Objects.equals(courier, delivery.courier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, order, courier);
    }
}

class Client {
    private String name;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

public class Main {
    public static void main(String[] args) {
        Client client = new Client("Иван Иванов");
        Order order = new Order(123, client);
        Product product = new Product("iPhone 14", "Смартфоны", 999.99);
        Delivery delivery = new Delivery(product, order, "Петр Сидоров");

        Delivery.setDefaultDeliveryDays(5);

        System.out.println("Заказ №" + delivery.getOrder().getNumber() +
                " клиента " + delivery.getOrder().getClient().getName() +
                " содержит товар: " + delivery.getProduct().getName() +
                " (" + delivery.getProduct().getCategory() + ")" +
                " Доставка осуществляется курьером: " + delivery.getCourier() +
                ". Способ доставки: " + Delivery.defaultShippingMethod +
                ", срок доставки: " + Delivery.defaultDeliveryDays + " дней.");


        //Пример использования equals() и hashCode()
        Delivery delivery2 = new Delivery(product, order, "Петр Сидоров");
        System.out.println("delivery equals delivery2: " + delivery.equals(delivery2));
        System.out.println("delivery hashCode: " + delivery.hashCode());
        System.out.println("delivery2 hashCode: " + delivery2.hashCode());


    }
}