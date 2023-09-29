// Databricks notebook source
dbutils.fs.mkdirs("/mnt/dados")

// COMMAND ----------

// MAGIC %python
// MAGIC dbutils.fs.ls("/mnt")

// COMMAND ----------

val configs = Map(
  "fs.azure.account.auth.type" -> "OAuth",
  "fs.azure.account.oauth.provider.type" -> "org.apache.hadoop.fs.azurebfs.oauth2.ClientCredsTokenProvider",
  "fs.azure.account.oauth2.client.id" -> "<application-id>",
  "fs.azure.account.oauth2.client.secret" -> dbutils.secrets.get(scope="<scope-name>",key="<service-credential-key-name>"),
  "fs.azure.account.oauth2.client.endpoint" -> "https://login.microsoftonline.com/<directory-id>/oauth2/token")

// Optionally, you can add <directory-name> to the source URI of your mount point.
dbutils.fs.mount(
  source = "abfss://imoveis@datalakealura.dfs.core.windows.net/",
  mountPoint = "/mnt/dados",
  extraConfigs = configs)

// COMMAND ----------

// MAGIC %python
// MAGIC dbutils.fs.ls("/mnt/dados")
