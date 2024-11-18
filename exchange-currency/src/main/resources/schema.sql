CREATE TABLE IF NOT EXISTS exchange_currency (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    from_currency VARCHAR(255) NOT NULL,
    to_currency VARCHAR(255) NOT NULL,
    conversion_multiple DOUBLE NOT NULL
);
