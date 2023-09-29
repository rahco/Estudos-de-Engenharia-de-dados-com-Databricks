from airflow import DAG
from airflow.providers.databricks.operators.databricks import DatabricksRunNowOperator
from datetime import datetime

with DAG(
  'Executando-notebook-etl',
  start_date=datetime(2023, 6, 1), 
  schedule_interval="0 9 * * *",  # Todos os dias as 9 da manhã
  ) as dag_executando_notebook_extracao:
    
    extraindo_dados = DatabricksRunNowOperator(
    task_id = 'Extraindo-conversoes',
    databricks_conn_id = 'databricks_default',
    job_id = "seu_job_id",
    notebook_params={"data_execucao": '{{data_interval_end.strftime("%Y-%m-%d")}}'}
  )
    
    transformando_dados = DatabricksRunNowOperator(
    task_id = 'transformando-dados',
    databricks_conn_id = 'databricks_default',
    job_id = "seu_job_id"
  )
    
    enviando_relatorio = DatabricksRunNowOperator(
    task_id = 'enviando-relatorio',
    databricks_conn_id = 'databricks_default',
    job_id = "seu_job_id"
  )
    
    extraindo_dados >> transformando_dados >> enviando_relatorio
