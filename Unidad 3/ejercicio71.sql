CREATE OR REPLACE FUNCTION lista_empleados_puesto(p_puesto VARCHAR)
    RETURNS SETOF empleados 
    LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
	mi_empleado empleados;
BEGIN
	FOR mi_empleado IN 
          SELECT * FROM empleados WHERE LOWER(puesto) LIKE LOWER(p_puesto)
	LOOP
		RETURN NEXT mi_empleado;
	END LOOP;
END;
$BODY$;
